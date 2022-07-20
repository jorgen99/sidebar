(ns hello-world.left-panel
  (:require
    [hello-world.icons :as icons]
    [hello-world.db :as db]
    [re-frame.core :as rf]))


(def init-db
  {:favorites [{:name "Team Blue"
                :folded true
                :members [{:rtid 424 :short-name "JLU" :full-name "Jörgen Lundberg" :color "resource-spanish-green"}
                          {:rtid 512 :short-name "OLF" :full-name "Olof Olsson" :color "resource-red"}
                          {:rtid 62 :short-name "LAN" :full-name "This Is A Really Long Ass Name" :color "resource-blue"}]}
               {:name "Team Red"
                :folded false
                :members [{:rtid 612 :short-name "APA" :full-name "Apa Apansson" :color "resource-safety-orange"}
                          {:rtid 712 :short-name "BEP" :full-name "Bepa Cepagren" :color "resource-mango"}
                          {:rtid 62 :short-name "LAN" :full-name "This Is A Really Long Ass Name" :color "resource-blue"}
                          {:rtid 424 :short-name "JLU" :full-name "Jörgen Lundberg" :color "resource-spanish-green"}
                          {:rtid 812 :short-name "KAK" :full-name "Kalle Kula" :color "resource-malachite"}
                          {:rtid 912 :short-name "LEL" :full-name "Leffe Lusis" :color "resource-electric-purple"}
                          {:rtid 512 :short-name "OLF" :full-name "Olof Olsson" :color "resource-red"}]}]
   :active [{:rtid 425 :short-name "JLU" :full-name "Jörgen Lundberg" :color "resource-spanish-green"}
            {:rtid 512 :short-name "OLF" :full-name "Olof Olsson" :color "resource-red"}
            {:rtid 62 :short-name "LAN" :full-name "This Is A Really Long Ass Name" :color "resource-blue"}]})
            

(def db-prefix ::state)


(db/reg-init-db! db-prefix init-db)

(rf/reg-sub ::favorites
            (fn [db _]
              (get-in db [db-prefix :favorites])))


(rf/reg-sub ::active
            (fn [db _]
              (get-in db [db-prefix :active])))


(rf/reg-event-db ::fold-favorite
                 (fn [db [_ favorite-idx fold]]
                   (assoc-in db [db-prefix :favorites favorite-idx :folded] fold)))


(defn- fold-favorite [favorite-idx fold]
  (rf/dispatch [::fold-favorite favorite-idx fold]))


(defn- heading []
  [:div.section.header.flex-row
   [:div.text.flex-grow "Resources"]
   [:div.pin.icon16
    icons/pin]
   [:div.plus-minus.icon16
    icons/dash-square]
   [:div.plus-minus.icon16.hidden
    icons/plus-square]])


(defn- search []
  [:div.section.search
   [:div.search-field.flex-row
    [:span.search-icon.icon16
     icons/search]
    [:input {:type "text"
             :placeholder "Search"}]]])


(defn- render-resource [resource minus-fn org-fn]
  (let [{:keys [rtid short-name full-name color]} resource
        color-var (str "var(--" color ")")]
    [:div.resorce.flex-row
     {:key rtid}
     [:div.short-name.center
      {:style {:background-color color-var}}
      short-name]
     [:div.full-name.flex-grow.clipped
      full-name]
     (when minus-fn
       [:div.icon16
        {:style {:color "var(--border-gray)"
                 :margin-right ".2rem"}
         :on-click (minus-fn rtid)}
        icons/dash-circle])
     (when org-fn
       [:div.icon16
        {:style {:color "var(--border-gray)"}
         :on-click (org-fn rtid)}
        icons/hierarchy])]))



(defn- active-section [active-resources]
  [:div.section.active
   [:div.pill
    [:div "Active"]]
   [:div.active-resources.flex-column
    (map render-resource active-resources)]])


(defn- render-favorite [idx {:keys [name folded members]}]
  (let [favorite-name name
        no-of-resources (count members)
        fold #(fold-favorite idx (not folded))]
    [:<>
     {:key name}
     [:div.favorite.flex-row
      [:div.border.resources.flex-row
       [:div.resource-icon.icon16 icons/group]
       [:div.number no-of-resources]]
      [:div.border.favorite-name.flex-row.flex-grow.pointer
       {:on-click fold}
       [:div.name favorite-name]
       [:div.resource-icon.icon16 (if folded
                                    icons/chevron-right
                                    icons/chevron-down)]]]
     [:div.flex-column
      {:class (str "members" (if folded " folded" " unfolded"))}
      (map render-resource members)]]))

(defn- favorites-section [favorites]
  [:div.section.favorites
   [:div.favorites-heading.flex-row
    [:div.favorites-icon.icon16 icons/star]
    [:div.heading.flex-grow "Favorites"]
    [:a "Edit Favorites"]]
   [:div.favorites-body
    [:div.list
     (map-indexed render-favorite favorites)]]
   [:div.add
    [:button.secondary.flex-row
     [:div.icon16 icons/star]
     [:div "Create Favorite"]]]])


(defn- resource-tree []
  [:div.section
   "Resource Tree"])


(defn render []
  (let [favorites @(rf/subscribe [::favorites])
        active-resources @(rf/subscribe [::active])]
    [:aside.left-panel.flex-column
     [heading]
     [search]
     [active-section active-resources]
     [favorites-section favorites]
     [resource-tree]])) 

