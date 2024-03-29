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

(defn re-set-icon [props]
  [:svg.re-set-icon
   {:width           24
    :height          24
    :viewBox         "0 0 24 24"
    :fill            "none"
    :stroke          "currentColor"
    :stroke-width    2
    :stroke-linecap  "round"
    :stroke-linejoin "round"
    :class           (if (get props :class) (get props :class))}
   [:polyline {:points "1 4 1 10 7 10"}]
   [:path {:d "M3.51 15a9 9 0 1 0 2.13-9.36L1 10"}]])

(defn loader-icon [props]
  [:svg.loader-icon
   {:width           24
    :height          24
    :viewBox         "0 0 24 24"
    :fill            "none"
    :stroke          "currentColor"
    :stroke-width    2
    :stroke-linecap  "round"
    :stroke-linejoin "round"
    :class           (if (get props :class) (get props :class))}
   [:line {:x1 "12" :y1 "2" :x2 "12" :y2 "6"}]
   [:line {:x1 "12" :y1 "18" :x2 "12" :y2 "22"}]
   [:line {:x1 "4.93" :y1 "4.93" :x2 "7.76" :y2 "7.76"}]
   [:line {:x1 "16.24" :y1 "16.24" :x2 "19.07" :y2 "19.07"}]
   [:line {:x1 "2" :y1 "12" :x2 "6" :y2 "12"}]
   [:line {:x1 "18" :y1 "12" :x2 "22" :y2 "12"}]
   [:line {:x1 "4.93" :y1 "19.07" :x2 "7.76" :y2 "16.24"}]
   [:line {:x1 "16.24" :y1 "7.76" :x2 "19.07" :y2 "4.93"}]])

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

