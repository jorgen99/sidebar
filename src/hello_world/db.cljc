(ns hello-world.db
  (:require
    [re-frame.core :as rf]))


(defn empty-db []
  {::left-menu-collapsed true})
   

(def db-inits (atom {}))


(defn init-db []
  (prn ::here "I'm beeing called")
  (merge (empty-db) @db-inits))


(defn reg-init-db! [namespace-key initial-value]
  (swap! db-inits assoc namespace-key initial-value))


(defn reg-sub! [k db-prefix f]
  (rf/reg-sub k
              (fn [db [_ & args]]
                (apply f (get db db-prefix) args))))


(defn reg-mut! [k db-prefix f]
  (rf/reg-event-db k
                   (fn [db [_ & args]]
                     (update db
                             db-prefix
                             (fn [state]
                               (apply f state args))))))


(defn reg-subs! [db-prefix subscriptions]
  (doseq [[k f] subscriptions]
    (reg-sub! k db-prefix f)))


(defn reg-muts! [db-prefix mutations]
  (doseq [[k f] mutations]
    (reg-mut! k db-prefix f)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def left-menu-collapsed ::left-menu-collapsed)


(defn set-left-menu-collapsed! [db collapsed]
  (prn "Sätter " ::left-menu-collapsed " till "  collapsed)
  (assoc db ::left-menu-collapsed collapsed))


