(ns hello-world.nav
  (:require
    [hello-world.icons :as icons]))


(defn- nav-item [icon text on-click]
  [:div.nav-item.flex-row
   {:on-click on-click
    :title text}
   [:span.nav-icon.flex-fixed
    icon]
   [:span.nav-text.flex-grow
    text]])
  
  

(defn render [toggle-fn]
  ;; Before the function called only once
  (fn []
    ;; called every re-render
    [:<>
     [:div.navigation-header.flex-column
      [:div.tacdis-logo
       [:img {:src "images/logo_tacdis.png"}]]]
     [:div.navigation-middle.flex-fixed
      (nav-item icons/calendar "Calendar" #(prn "Calendar"))]
     [:div.navigation-middle.flex-grow.nav-divider
      (nav-item icons/hierarchy "Resource Tree" #(prn "Resource Tree"))
      (nav-item icons/key-icon "Permissions" #(prn "Permissions"))
      (nav-item icons/calendar-range "Schedule" #(prn "Schedule"))
      (nav-item icons/clockwise "VPS Rotation" #(prn "VPS Rotatino"))
      (nav-item icons/building "Dealer Setup" #(prn "Dealer Setup"))
      (nav-item icons/calendar-week "Slot Appointments" #(prn "Slot Appointments"))]
     [:div.navigation-footer.flex-fixed.nav-divider
      [:div.nav-item.footer-items
       {:title "Settings"
        :on-click #(prn "Settings")}
       [:span.nav-icon.flex-fixed
        icons/cog]
       [:span.nav-text.flex-grow
        "Settings"]
       [:span.nav-icon.flex-fixed
        {:on-click toggle-fn}
        icons/double-chevron-left]]]]))

