(ns hello-world.calendar-main)
  

(defn render []
  ;; Before the function called only once
  (fn []
    ;; called every re-render
    [:div
     [:button.primary
      "Label"]
     [:button.approve
      "Label"]
     [:button.delete
      "Label"]
     [:button.secondary
      "Label"]]))

