(ns challenge.app.events
  (:require
    [re-frame.core :as rf]
    [challenge.db :as db]
    [cljs.spec.alpha :as s]
    [day8.re-frame.tracing :refer-macros [fn-traced]]
    [challenge.utils :refer [get-request-map]]))

(rf/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))

(rf/reg-event-db
  ::set-active-panel
  (fn-traced [db [_ active-panel]]
             (assoc db :active-panel active-panel)))

;; --helpers
(defn index-by-id [col]
  (into {} (map (juxt :id identity) col)))

;; fetch-todos
(rf/reg-event-fx
  :fetch-todos
  (fn [{:keys [db]} [_ response]]
    {:http-xhrio (get-request-map :get "" :fetch-todos-success :common-on-failure nil)
     :db         (-> db
                     (assoc :loading true)
                     (assoc :error nil)
                     )
     }))

(rf/reg-event-db
  :fetch-todos-success
  (fn [db [_ response]]
    (-> db
        (assoc :loading false)
        (assoc :todos (index-by-id (:data response)))
        )))

(rf/reg-event-db
  :common-on-failure
  (fn [db [_ error]]
    (-> db
        (assoc :error error)
        (assoc :loading false)
        )))

;; -- create-todo
(rf/reg-event-fx
  :create-todo
  (fn [{:keys [db]} [_ data]]
    {:http-xhrio
         (get-request-map
           :post
           ""
           :create-todo-success
           :common-on-failure
           {:title   data
            :done    false
            :created (js/Date.)
            })
     :db (-> db
             (assoc :loading true)
             (assoc :error nil)
             )}))

(rf/reg-event-db
  :create-todo-success
  (fn [db [_ response]]
    (-> db
        (assoc-in [:todos (:id (:data response))] (:data response))
        (assoc :loading false)
        )))

;; -- toggle-todo
(rf/reg-event-fx
  :toggle-todo
  (fn [{:keys [db]} [_ data]]
    {:http-xhrio (get-request-map
                   :patch
                   (str "/" (:id data))
                   :toggle-todo-success
                   :common-on-failure
                   {:done (not (:done data))})
     :db         (-> db
                     (assoc :loading true)
                     (assoc :error nil)
                     )}))


(rf/reg-event-db
  :toggle-todo-success
  (fn [db [_ response]]
    (-> db
        (assoc-in [:todos (:id (:data response))] (:data response))
        (assoc :loading false)
        )
    ))

;; --delete-todo
(rf/reg-event-fx
  :delete-todo
  (fn [{:keys [db]} [_ id]]
    {:http-xhrio (get-request-map
                   :delete
                   (str "/" id)
                   [:delete-todo-success id]
                   :common-on-failure
                   nil)
     :db         (-> db
                     (assoc :loading true)
                     (assoc :error nil)
                     )}))

(rf/reg-event-db
  :delete-todo-success
  (fn [db [_ id]]
    (-> db
        (update-in [:todos] dissoc (:todos db) id)
        (assoc :loading false)
        )
    ))
