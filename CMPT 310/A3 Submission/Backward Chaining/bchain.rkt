#lang racket
;Test Case: ((q (p)) (p (l m)) (m (b l)) (l (a p)) (l (a b)) (a) (b))
;Test Case (Given): ((p) (q (p)) (r (q s t)) (s (p u)) (r (q h)) (t) (h (t)) (u (v)))
;Test Case: ((a) (b) (p (a b c d e)) (q (c e)) (c (a b)) (r (a b d)) (d))

;Get rules
(define (bchain filename query) ;testfile needs to be in same location as this file
  (define rules (file->list filename)); convert rules to list
  (printf "\nRules: ")
  (define que (list query))
  (display (car rules))
  (bchainRec (car rules) que)
  )

(define (bchainRec list1 goals) ;Following pseudocode given
  ;(set! goals (remove-duplicates goals))
  (printf "\nCurrent Goals:")
  (display goals)
  (when
      (null? goals) (begin (values true) (printf "\n#t Success!") (exit))
    )
  
  (define a (car goals))
  (define head (list))
  (set! goals (cdr goals))
  
  (for ([i (in-range 0 (length list1))])
    ;(display (car(car (list-ref list1 i))))
    (cond
     ( (> (length (list-ref list1 i)) 1) 
       (when
           (equal? a (car (list-ref list1 i))) (set! head (append head (list (list-ref list1 i)))); add good rules to list
         ));(display head)
     ( (eq? (length (list-ref list1 i)) 1) ; 
     (when
           (equal? a (car(list-ref list1 i))) (set! head (append head (list (list-ref list1 i)))); add good rules to list
         ))
     
    )
    )

  (for ([j (in-range 0 (length head))]) ;checking each body of good rules
    (cond ( (> (length (list-ref head j)) 1) ; for regular rule
            (if (bchainRec list1 (append (flatten(cdr (list-ref head j))) goals)) ;Recurses with body of chosen rule
              (begin (printf "\nAdded to our Goals list:") (display (flatten(cdr (list-ref head j) (values true))))) (begin (values false) (printf "\nCannot prove atom in current rule: ") (display a))))
          ( (eq? (length (list-ref head j)) 1) ; for fact
            (begin (values true) (printf "\nProven, because it is a fact: ") (display a) (bchainRec list1 goals)))
          )
    )
  
  (values false); Cannot be proven using given rules
 )


