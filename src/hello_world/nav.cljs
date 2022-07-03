(ns hello-world.nav
  (:require
    [hello-world.icons :as icons]))
  

(defn render []
  ;; Before the function called only once
  (fn []
    ;; called every re-render
    [:<>
     [:div.navigation-header.flex-fixed
      "Tactis logo"]
     [:div.navigation-middle.flex-grow
      "navigation middle"]
     [:div.navigation-footer.flex-fixed
      [:a.nav-item.flex-row
       [:span.nav-icon.flex-fixed
        icons/cog]
       [:span.nav-text.flex-grow
        "Settings"]
       [:span.nav-icon.flex-fixed
        icons/double-chevron-left]]]]))

