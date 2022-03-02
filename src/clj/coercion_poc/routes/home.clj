(ns coercion-poc.routes.home
  (:require
   [coercion-poc.layout :as layout]
   [clojure.java.io :as io]
   [coercion-poc.middleware :as middleware]
   [ring.util.response :refer [response]]
   [clojure.spec.alpha :as s]
   [reitit.ring.coercion :as coercion]
   [reitit.coercion.spec :as spec]
   [reitit.ring.middleware.exception :as exception]
   [muuntaja.middleware :refer [wrap-format wrap-params]]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.swagger :as swagger]
   [reitit.ring.middleware.multipart :as multipart]
   ))

(defn home-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page [request]
  (layout/render request "about.html"))

(s/def ::email string?)
(s/def ::password int?)
(s/def ::login-params (s/keys :req-un [::email ::password] ))


(defn login-handler [{:keys [params parameters] :as req}]
  (prn "params " params)
  (prn "parameters " parameters)
  (response {:foo :bar}))

(defn home-routes []
  [""
   {:middleware [;;middleware/wrap-formats
                 ;; swagger features
                 swagger/swagger-feature
                 ;; query-params & form-params
                 parameters/parameters-middleware
                 ;; content-negotiation
                 muuntaja/format-negotiate-middleware
                 ;; encoding response body
                 muuntaja/format-response-middleware
                 ;; exception handling
                 exception/exception-middleware
                 ;; decoding request body
                 muuntaja/format-request-middleware
                 ;; coercing response bodys
                 coercion/coerce-response-middleware
                 ;; coercing request parameters
                 coercion/coerce-request-middleware
                 ;; multipart
                 multipart/multipart-middleware
                 ]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/api/login" {:coercion spec/coercion
                  :post login-handler
                  :parameters {:form ::login-params}
                  }]])
