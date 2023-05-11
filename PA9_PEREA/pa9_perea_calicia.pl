listLen([], 0).
listLen([_|T], L) :- listLen(T, L1), L is L1 + 1.

dropK(L, 1, [_|T]) :- L = T.
dropK([H|T], K, [H|L]) :- K > 1, K1 is K - 1, dropK(T, K1, L).

divide([], [], L, _).
divide(L1, L2, [H|T], N) :- N > 0, N1 is N - 1, divide(L11, L2, T, N1), L1 = [H|L11].
divide(L1, L2, L, N) :- N =< 0, L1 = [], L2 = L.

% Example calls
% listLen
% listLen([1, 2, 3], L).
% L = 3.

% dropK
% dropK(X, 2, [6,3,5,2,8]).
% X = [6,5,8].

% divide
% divide(X, Y, [], 0).
% X = Y, Y = []
% divide(X, Y, [3], 0).
% X = [3],
% Y = []
% divide(X, Y, [3, 1], 0).
% X = [3, 1],
% Y = []
% divide(X, Y, [3, 1, 9], 0).
% X = [3, 1, 9],
% Y = []
% divide(X, Y, [3, 1, 9, 5, 8], 3).
% X = [3, 1, 9],
% Y = [5, 8]
