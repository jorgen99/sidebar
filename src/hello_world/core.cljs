(ns ^:figwheel-hooks hello-world.core
  (:require
   [goog.dom :as gdom]
   [hello-world.app :as app]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))


;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!"}))


(defn get-app-element []
  (gdom/getElement "app"))


(defn ui
  "The Organizer UI"
  []
  app/page)


(defn mount [el]
  (rdom/render [ui] el))


(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))


;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element))
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

