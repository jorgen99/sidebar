(ns hello-world.app
  (:require
    [hello-world.nav :as nav]
    [hello-world.calendar-main :as calendar-main]))


(defn page []
  [:main.content.grid
   [:div.header
    "Header"]
   [:aside.navigation.flex-column
    [nav/render]]
   [:div.calendar-main
    [calendar-main/render]]
   [:aside.appointment-edit
    "Edit appointment"]])


  
