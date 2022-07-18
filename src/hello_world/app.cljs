(ns hello-world.app
  (:require
    [hello-world.calendar-main :as calendar-main]
    [hello-world.db :as db]
    [hello-world.left-panel :as left-panel]
    [hello-world.nav :as nav]
    [re-frame.core :as rf]))


(def init-db
  {})


(def db-prefix ::state)


(db/reg-init-db! db-prefix init-db)


(rf/reg-sub :left-menu-collapsed
            (fn [db _]
              (db/left-menu-collapsed db)))


(rf/reg-event-fx ::set-left-menu-collapsed
                 (fn [{:keys [db]} [_ collapsed]]
                   {:db (db/set-left-menu-collapsed! db collapsed)}))


(defn- set-left-menu-collapsed [expanded]
  (prn "sätter expanded till " expanded)
  (rf/dispatch [::set-left-menu-collapsed expanded]))


(defn page []
  (prn ::page "Page called again!")
  (let [left-menu-collapsed (rf/subscribe [:left-menu-collapsed])
        _ (prn ::collapsed left-menu-collapsed)
        toggle-collapsed (fn [event]
                           (js/console.log "Clicked " @left-menu-collapsed)
                           (.stopPropagation event)
                           (set-left-menu-collapsed (not @left-menu-collapsed)))]
    [:main.content.grid
     [:div.tacdis-header.flex-row
      "Header"]
     [:aside.navigation.flex-column
      {:class (str "navigation flex-column"
                   (when @left-menu-collapsed " collapsed"))}
      [nav/render toggle-collapsed]]
     [:div.calendar-main
      [calendar-main/render]]
     [left-panel/render]]))
  
