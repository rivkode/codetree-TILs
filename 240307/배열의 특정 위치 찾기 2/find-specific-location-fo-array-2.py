lst = list(map(int, input().split()))

s_1 = sum(lst[0::2])
s_2 = sum(lst[1::2])

if s_1 > s_2:
    print(s_1 - s_2)
else:
    print(s_2 - s_1)