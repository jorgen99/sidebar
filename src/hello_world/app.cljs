(ns hello-world.app
  (:require
    [hello-world.nav :as nav]
    [hello-world.calendar-main :as calendar-main]
    [re-frame.core :as rf]))




(defn page []
    (fn []
      [:main.content.grid
       [:div.header.flex-row
        "Header"]
       [:aside.navigation.flex-column
        [nav/render]]
       [:div.calendar-main
        [calendar-main/render]]
       [:aside.appointment-edit
        "Edit appointment"]]))


  
