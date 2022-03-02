(ns coercion-poc.scripts
  (:require [clj-http.client]))


(:body (clj-http.client/post "http://localhost:3000/api/login"
                             {:form-params {:foo :bar :email "admin@admin.com" :password 123}}))

;;(:body (clj-http.client/get "http://localhost:3000/"))
