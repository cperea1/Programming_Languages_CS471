#lang racket/base
(require racket/list) ; import the racket/list library

; Given an integer n, returns true if n is a power of four, else returns false
(define (powerfour n)
  (cond ((= n 0) 
         #f)
        ((= (expt 4 (floor (log n 4))) n) 
         #t)
        (else 
         #f)))

; Inserts an element at a given position into a list using recursion
(define (insert-at lst elem pos)
  (cond ((null? lst) ; if list is empty
         (if (= pos 1) ; and position is 1
             (cons elem lst) ; add element to list
             '())) ; else, return empty list
        ((= pos 1) ; if position is 1
         (cons elem lst)) ; add element to beginning of list
        (else ; else, add first element to list and recurse with rest of list
         (cons (car lst) (insert-at (cdr lst) elem (- pos 1))))))

; Given a list, returns 1 if the list is a palindrome, 0 otherwise
(define (palindromep lst)
  (cond ((null? lst) #t) ; empty list is a palindrome
        ((null? (cdr lst)) #t) ; single-element list is a palindrome
        (else (and (equal? (car lst) (last lst)) ; check if first and last elements are equal
                   (palindromep (drop-right (cdr lst) 1)))))) ; recurse with middle elements of list

; Determines whether a given integer number is prime or not
; Returns 0 if it is prime, 1 if it is not prime
(define (ifPrime n)
  (define (divisible? x y)
    (= (modulo x y) 0))
  (define (check-prime m)
    (cond ((<= m 1) 0) ; if m is less than or equal to 1, n is prime
          ((divisible? n m) 1) ; if n is divisible by m, n is not prime
          (else (check-prime (- m 1)))) ; else, recurse with m - 1
    )
  (check-prime (floor (sqrt n)))) ; start with m = sqrt(n)

; Test the functions
(displayln "Powerfour!")
(displayln (powerfour 16))
(displayln (powerfour 64))
(displayln (powerfour 100))
(displayln (powerfour 0))

(displayln "insert-at!")
(displayln (insert-at '(1 2 3 4) 0 3))
(displayln (insert-at '(a b c) 'x 1))
(displayln (insert-at '() 5 1))
(displayln (insert-at '(a b c d) 'x 3))

(displayln "Palindrome!")
(displayln (palindromep '(1 2 3 2 1)))
(displayln (palindromep '(a b c c b a)))
(displayln (palindromep '(1 2 3 4)))
(displayln (palindromep '(a b c d e)))

(displayln "Prime!")
(displayln (ifPrime 17))
(displayln (ifPrime 15))
(displayln (ifPrime 23))
(displayln (ifPrime 49))