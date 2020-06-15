(ns challenge.app.views
  (:require
    [re-frame.core :as rf]
    [reagent.core :as r]
    [clojure.string :as str]
    [challenge.app.subs :as subs]
    [challenge.app.events :as events]
    [challenge.icons :refer [archieve-icon delete-icon done-icon trash-icon re-set-icon loader-icon]]))


;; helpers
(defn format-date
  [date]
  (.toDateString (js/Date. date)))

;; -- toggle-todo-button
(defn todo-toggle-button [done]
  [:a.toggle-todo-button
   (if done [re-set-icon] [done-icon])])

;; -- delete-todo-button
(defn todo-delete-button [id]
  [:a.delete-todo-button
   {:href     "#"
    :on-click #(rf/dispatch [:delete-todo id])}
   [trash-icon]])

(defn todo-item [todo]
  [:div.todo-item
   [:div.todo-data
    [:h2.todo-title
     {:class (str (if (get todo :done) "done"))}
     (get todo :title)]
    [:p.created-at (str "Created: " (format-date (:created todo)))]]
   [:div.todo-toolbox
    (todo-toggle-button (get todo :done))
    (todo-delete-button (get todo :id))]
   [:div.todo-status-color {:class (if (get todo :done) "done")}]])

;; -- add-todo
(defn create-todo []
  (let [val (r/atom "")]
    [:div.create-todo
     [:input.create-todo-input
      {:type        "text"
       :placeholder "Create To Do"
       :on-change   #(reset! val (-> % .-target .-value))}]
     [:a.create-todo-button
      {:href     "#"
       :on-click #(rf/dispatch [:create-todo @val])}
      [done-icon]]
     ]
    ))

;; -- there-is-nothing-todo
(defn nothing-to-do []
  [:div.nothing-to-do
   [:h2.you-are-done "You are done! There is nothing to do.."]
   [:div.status-color-bar]])

;; -- todo-container
(defn todo-container [todos]
  [:div.todo-container
   [create-todo]
   (if (pos? (count todos))
     (for [todo todos]
       ^{:key (:id todo)}
       [todo-item todo])
     [nothing-to-do])
   ])

;; -- App
(defn app []
  (r/create-class
    {:component-did-mount #(rf/dispatch [:fetch-todos])
     :reagent-render
                          (fn []
                            (let [todos @(rf/subscribe [:todos])]
                              [todo-container todos]
                              ))}))

;(defn main-panel []
;  (let [active-panel (rf/subscribe [::subs/active-panel])]
;    [show-panel @active-panel]))
