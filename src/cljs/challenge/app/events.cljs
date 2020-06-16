(ns challenge.app.events
  (:require
    [re-frame.core :as rf]
    [challenge.db :as db]
    [day8.re-frame.tracing :refer-macros [fn-traced]]
    [ajax.core :as ajax]
    [challenge.api :refer [make-request]]))

(rf/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))

(rf/reg-event-db
  ::set-active-panel
  (fn-traced [db [_ active-panel]]
             (assoc db :active-panel active-panel)))

;;
(defn index-by-id [col]
  (into {} (map (juxt :id identity) col)))

;; fetch-todos
(rf/reg-event-fx
  :fetch-todos
  (fn [_ _]
    (make-request :get "" :fetch-todos-success :common-on-failure nil)))

(rf/reg-event-db
  :fetch-todos-success
  (fn [db [_ data]]
    (assoc db :todos (index-by-id data))))

(rf/reg-event-db
  :common-on-failure
  (fn [db [_ error]]
    (assoc db :error error)))

;; --create-todo
(rf/reg-event-fx
  :create-todo
  (fn [_ [_ data]]
    (make-request :post "" :create-todo-success :common-on-failure {:title data})))

(rf/reg-event-db
  :create-todo-success
  (fn [db [_ data]]
    (assoc-in db [:todos (:id data)] data)))

;; -- toggle-todo
(rf/reg-event-fx
  :toggle-todo
  (fn [_ [_ data]]
    (make-request :patch (str "/" (:id data)) :toggle-todo-success :common-on-failure {:done (not (:done data))})))

(rf/reg-event-db
  :toggle-todo-success
  (fn [db [_ data]]
    (assoc-in db [:todos (:id data)] data)
    ))

;; --delete-todo
(rf/reg-event-fx
  :delete-todo
  (fn [_ [_ id]]
    (make-request :delete (str "/" id) [:delete-todo-success id] :common-on-failure nil)
    ))

(rf/reg-event-db
  :delete-todo-success
  (fn [db [_ id]]
    (update-in db [:todos] dissoc (:todos db) id)))

