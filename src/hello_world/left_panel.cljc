(ns hello-world.left-panel
  (:require
    [hello-world.icons :as icons]))


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


(defn- favorite [short-name full-name color]
  (let [c (str "var(--" color ")")
        _ (prn ::color c)]
    [:div.favorite-resource.flex-row
     [:div.short-name.center
      {:style {:background-color c}} 
      short-name]
     [:div.full-name.flex-grow.clipped
      full-name]
     [:div.icon-border-round
      [:div.inner-icon
       icons/dash]]
     [:div.icon-border-round
      [:div.inner-icon
       {:style {:margin"-1px 0 0 0px"}}
       icons/hierarchy]]]))



(defn- active []
  [:div.section.active
   [:div.pill
    [:div "Active"]]
   [:div.favorite-resources.flex-column
    [favorite "JLU" "Jörgen Lundberg" "resource-spanish-green"]
    [favorite "OLF" "Olof Olsson" "resource-red"]
    [favorite "LAN" "This Is A Really Long Ass Name" "resource-blue"]]])

(defn- favorites []
  [:div.section
   "Favorites"])


(defn- resource-tree []
  [:div.section
   "Resource Tree"])


(defn render []
  [:aside.left-panel.flex-column
   [heading]
   [search]
   [active]
   [favorites]
   [resource-tree]]) 

