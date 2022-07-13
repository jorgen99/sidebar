(ns hello-world.app
  (:require
    [hello-world.nav :as nav]
    [hello-world.calendar-main :as calendar-main]
    [hello-world.left-panel :as left-panel]
    [re-frame.core :as rf]))




(defn page []
    (fn []
      [:main.content.grid
       [:div.tacdis-header.flex-row
        "Header"]
       [:aside.navigation.flex-column
        [nav/render]]
       [:div.calendar-main
        [calendar-main/render]]
       [left-panel/render]]))
  
