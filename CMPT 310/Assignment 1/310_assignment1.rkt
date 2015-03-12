#lang racket
; Property List 
(define a '(name John age 26 parents (Mary Bill) occupation student))

;Functions

(define (getp prop l)
     (cond 
       ((equal? prop (car l)) (car (cdr l))); checks for property if equal uses next element which is the actual property
       ((null? (cdr l)) #f); If property does not exist return false
       (else (getp prop (cdr l)))); Call function again with without first element
  );; useful for putp and remp to check for property in list

(define (putp prop val l)  
  (cond ;base case replace first property which is index 2
    ((not(getp prop l)) (cons prop (cons val l))); using getp ;checks to see if property exists, if no then inserts at the front.
    ((equal? prop (car l)) (cons (car l) (cons val (cdr (cdr l))))); check for equality of attribute, if true replace value
    (else  (cons (car l) (putp prop val (cdr l)))); keeps track of front of the list
    )
  )

(define (remp prop l)  
  (cond ;base case remove first property and attribute pair (name)
    ((not(getp prop l)) #f) ;checks to see if property exists, if no then returns false 
    ((equal? prop (car l))  (cdr (cdr l))); check for equality and remove
    (else  (cons (car l) (remp prop (cdr l))))
    )
  )

;nothing against calling other methods I created, right?