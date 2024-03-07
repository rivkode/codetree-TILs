lst = list(map(int, input().split()))
t_filtered = sum(lst[1::2])
avg = round(sum(lst[2::3]) / (len(lst) // 3), 1)

print(t_filtered, end=' ')
print(avg)