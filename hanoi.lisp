(in-package :shop2-user)

; attempting htn domain for towers of hanoi recursively


(defdomain hanoi (
    (:operator (!move ?disc ?fromTower ?toTower) () ((on ?disc ?fromTower)) ((on ?disc ?toTower)))

    (:method (moveMultipleDiscs (?top . ?l ) ?fromTower ?toTower ?viaTower)
       ((isSmallest ?top))
       ((!move ?top ?fromTower ?toTower))
       ()
       ((moveMultipleDiscs ?l ?fromTower ?viaTower ?toTower) (!move ?top ?fromTower ?toTower) (moveMultipleDiscs ?l ?viaTower ?toTower ?fromTower)))))

    (defproblem three-discs hanoi
        ( (isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1)) ((moveMultipleDiscs (d3 d2 d1) t1 t3 t2)))

    (defproblem four-discs hanoi
        ( (isSmallest d1) ) ((moveMultipleDiscs (d4 d3 d2 d1) t1 t3 t2)))

    (defproblem five-discs hanoi
        ( (isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1) (on d4 t1) (on d5 t1)) ((moveMultipleDiscs (d5 d4 d3 d2 d1) t1 t3 t2)))

    (defproblem six-discs hanoi
        ( (isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1) (on d4 t1) (on d5 t1) (on d6 t1)) ((moveMultipleDiscs (d6 d5 d4 d3 d2 d1) t1 t3 t2)))

    (defproblem seven-discs hanoi
        ( (isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1) (on d4 t1) (on d5 t1) (on d6 t1) (on d7 t1)) ((moveMultipleDiscs (d7 d6 d5 d4 d3 d2 d1) t1 t3 t2)))
        
    (defproblem eight-discs hanoi
        ( (isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1) (on d4 t1) (on d5 t1) (on d6 t1) (on d7 t1) (on d8 t1)) ((moveMultipleDiscs (d8 d7 d6 d5 d4 d3 d2 d1) t1 t3 t2)))

    (defproblem nine-discs hanoi
        ( (isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1) (on d4 t1) (on d5 t1) (on d6 t1) (on d7 t1) (on d8 t1) (on d9 t1)) ((moveMultipleDiscs (d9 d8 d7 d6 d5 d4 d3 d2 d1) t1 t3 t2)))

    (defproblem ten-discs hanoi
        ( (isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1) (on d4 t1) (on d5 t1) (on d6 t1) (on d7 t1) (on d8 t1) (on d9 t1) (on d10 t1)) ((moveMultipleDiscs (d10 d9 d8 d7 d6 d5 d4 d3 d2 d1) t1 t3 t2)))

    (defproblem eleven-discs hanoi
        ((isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1) (on d4 t1) (on d5 t1) (on d6 t1) (on d7 t1) (on d8 t1) (on d9 t1) (on d10 t1) (on d11 t1)) ((moveMultipleDiscs (d11 d10 d9 d8 d7 d6 d5 d4 d3 d2 d1) t1 t3 t2)))
     
    (defproblem twelve-discs hanoi
        ((isSmallest d1) (on d1 t1) (on d2 t1) (on d3 t1) (on d4 t1) (on d5 t1) (on d6 t1) (on d7 t1) (on d8 t1) (on d9 t1) (on d10 t1) (on d11 t1) (on d12 t1) ((moveMultipleDiscs (d12 d11 d10 d9 d8 d7 d6 d5 d4 d3 d2 d1) t1 t3 t2))))

;(find-plans 'three-discs :verbose :plans)
;(find-plans 'four-discs :verbose :plans)
;(find-plans 'five-discs :verbose :plans)
;(find-plans 'six-discs :verbose :plans)
;(find-plans 'seven-discs :verbose :plans)
;(find-plans 'eight-discs :verbose :plans)
;(find-plans 'nine-discs :verbose :plans)
;(find-plans 'ten-discs :verbose :plans)
;(find-plans 'eleven-discs :verbose :plans)
;(find-plans 'twelve-discs :verbose :plans)
