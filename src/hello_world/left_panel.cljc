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


(defn- active-resource [short-name full-name color]
  (let [c (str "var(--" color ")")
        _ (prn ::color c)]
    [:div.active-resource.flex-row
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
   [:div.active-resources.flex-column
    [active-resource "JLU" "Jörgen Lundberg" "resource-spanish-green"]
    [active-resource "OLF" "Olof Olsson" "resource-red"]
    [active-resource "LAN" "This Is A Really Long Ass Name" "resource-blue"]]])

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

