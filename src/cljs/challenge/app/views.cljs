(ns challenge.app.views
  (:require
    [re-frame.core :as rf]
    [reagent.core :as r]
    [clojure.string]
    [challenge.app.subs]
    [challenge.app.events]
    [challenge.icons :refer [archieve-icon delete-icon done-icon trash-icon re-set-icon loader-icon]]))

;; helpers
(defn format-date
  [date]
  (.toLocaleString (js/Date. date)))

;; -- toggle-todo-button
(defn todo-toggle-button [id done]
  [:a.toggle-todo-button
   {:on-click #(rf/dispatch [:toggle-todo {:id id :done done}])
    :class    (if done "is-done" "todo")}
   (if done [re-set-icon] [done-icon])])

;; -- delete-todo-button
(defn todo-delete-button [id]
  [:a.delete-todo-button
   {:on-click #(rf/dispatch [:delete-todo id])}
   [trash-icon]])

;; -- todo-item
(defn todo-item [todo]
  [:div.todo-item
   [:div.todo-data
    [:h2.todo-title
     {:class (if (:done todo) "done")}
     (:title todo)]
    [:p.created-at (str "Created: " (format-date (:created todo)))]]
   [:div.todo-toolbox
    (todo-toggle-button (:id todo) (:done todo))
    (todo-delete-button (:id todo))]
   [:div.todo-status-color {:class (if (:done todo) "done")}]])

;; -- create-todo
(defn create-todo []
  (let [val (r/atom "")]
    [:div.create-todo
     [:input.create-todo-input
      {:type        "text"
       :placeholder "Create To Do"
       :on-change   #(reset! val (-> % .-target .-value))
       }]
     [:a.create-todo-button
      {:on-click #(rf/dispatch [:create-todo @val])}
      [done-icon]]
     ]
    ))

;; -- there-is-nothing-todo
(defn nothing-to-do []
  [:div.nothing-to-do
   [:h2.you-are-done "You are done! There is nothing to do.."]
   [:div.status-color-bar]])

;; -- loading-container
(defn loading-container []
  [:div.loading-container
   [loader-icon]
   [:h1.loading-text "Loading.."]
   ])

;; -- todo-container
(defn todo-container [todos is-loading]
  [:div.todo-container
   [create-todo]
   (if (pos? (count todos))
     (for [todo todos]
       ^{:key (:id todo)}
       [todo-item todo])
     [nothing-to-do])
   (if (pos? is-loading) [loading-container])
   ])

;; -- error
(defn error-container [error]
  [:div.error-container
   [:h2.error-title "Something Went Wrong!"]
   [:p.error-message (if (:error-message (:response error))
                       (str (:error-message (:response error)))
                       (str (:status-text error)))]])

;; -- App
(defn app []
  (r/create-class
    {:component-did-mount #(rf/dispatch [:fetch-todos])
     :reagent-render
                          (fn []
                            (let [todos @(rf/subscribe [:todos])
                                  error @(rf/subscribe [:error])
                                  loading @(rf/subscribe [:loading])]
                              [:<>
                               [todo-container todos loading]
                               (when error [error-container error])
                               ]
                              ))}))

;(defn main-panel []
;  (let [active-panel (rf/subscribe [::subs/active-panel])]
;    [show-panel @active-panel]))
