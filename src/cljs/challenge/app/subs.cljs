(ns challenge.app.subs
  (:require
    [re-frame.core :refer [reg-sub subscribe]]))

(reg-sub
  ::name
  (fn [db]
    (:name db)))

(reg-sub
  ::active-panel
  (fn [db _]
    (:active-panel db)))

;;
(reg-sub
  :todos
  (fn [db]
    (sort-by :id > (vals (:todos db)))))

(reg-sub
  :error
  (fn [db]
    (:error db)))
