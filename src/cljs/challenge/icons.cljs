(ns challenge.icons)

(defn archieve-icon [props]
  [:svg.archieve-icon
   {:width           24
    :height          24
    :viewBox         "0 0 24 24"
    :fill            "none"
    :stroke          "currentColor"
    :stroke-width    2
    :stroke-linecap  "round"
    :stroke-linejoin "round"
    :class           (if (get props :class) (get props :class))}
   [:polyline {:points "21 8 21 21 3 21 3 8"}]
   [:rect {:x 1 :y 3 :width 22 :height 5}]
   [:line {:x1 10 :y1 12 :x2 14 :y2 12}]])

(defn delete-icon [props]
  [:svg.delete-icon
   {:width           24
    :height          24
    :viewBox         "0 0 24 24"
    :fill            "none"
    :stroke          "currentColor"
    :stroke-width    2
    :stroke-linecap  "round"
    :stroke-linejoin "round"
    :class           (if (get props :class) (get props :class))}
   [:path {:d "M21 4H8l-7 8 7 8h13a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2z"}]
   [:line {:x1 18 :y1 9 :x2 12 :y2 15}]
   [:line {:x1 12 :y1 9 :x2 18 :y2 15}]])

(defn done-icon [props]
  [:svg.done-icon
   {:width           24
    :height          24
    :viewBox         "0 0 24 24"
    :fill            "none"
    :stroke          "currentColor"
    :stroke-width    2
    :stroke-linecap  "round"
    :stroke-linejoin "round"
    :class           (if (get props :class) (get props :class))}
   [:polyline {:points "20 6 9 17 4 12"}]])

(defn trash-icon [props]
  [:svg.trash-icon
   {:width           24
    :height          24
    :viewBox         "0 0 24 24"
    :fill            "none"
    :stroke          "currentColor"
    :stroke-width    2
    :stroke-linecap  "round"
    :stroke-linejoin "round"
    :class           (if (get props :class) (get props :class))}
   [:polyline {:points "3 6 5 6 21 6"}]
   [:path
    {:d "M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"}]
   [:line {:x1 10 :y1 11 :x2 10 :y2 17}]
   [:line {:x1 14 :y1 11 :x2 14 :y2 17}]])

