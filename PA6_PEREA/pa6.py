import threading
import random
import time

class Matrix:
    def __init__(self, n):
        self.n = n
        self.matrix = [[0 for i in range(n)] for j in range(n)]
        for i in range(n):
            for j in range(n):
                self.matrix[i][j] = random.randint(0, 2 ** 20)
        self.max = self.matrix[0][0]
        self.min = self.matrix[0][0]
        self.sum = 0
        self.time = 0

    def calculate(self):
        for i in range(self.n):
            for j in range(self.n):
                self.max = max(self.max, self.matrix[i][j])
                self.min = min(self.min, self.matrix[i][j])
                self.sum += self.matrix[i][j]
        self.avg = self.sum / (self.n * self.n)

    def calculate_thread(self, row, results):
        max_val = row[0]
        min_val = row[0]
        s = 0
        for i in range(self.n):
            max_val = max(max_val, row[i])
            min_val = min(min_val, row[i])
            s += row[i]
        results.append((max_val, min_val, s))

    def calculate_with_threads(self):
        start_time = time.time()
        results = []
        threads = []
        for i in range(self.n):
            t = threading.Thread(target=self.calculate_thread, args=(self.matrix[i], results,))
            threads.append(t)
            t.start()
        for t in threads:
            t.join()
        self.max = results[0][0]
        self.min = results[0][1]
        self.sum = results[0][2]
        for i in range(1, self.n):
            self.max = max(self.max, results[i][0])
            self.min = min(self.min, results[i][1])
            self.sum += results[i][2]
        self.avg = self.sum / (self.n * self.n)
        end_time = time.time()
        self.time = end_time - start_time

n = int(input("Enter the dimension of the matrix: "))
m = Matrix(n)
m.calculate()
print("Max:", m.max)
print("Min:", m.min)
print("Avg:", m.avg)
print("Time taken:", m.time)

m.calculate_with_threads()
print("Max with threads:", m.max)
print("Min with threads:", m.min)
print("Avg with threads:", m.avg)
print("Time taken with threads:", m.time)
