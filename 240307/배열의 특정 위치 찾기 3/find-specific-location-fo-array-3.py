# 첫번째 풀이

# lst = list(map(int, input().split()))
# res_idx = 0

# for i in range(len(lst)):
#     if lst[i] == 0:
#         res_idx = i
#         break

# res = lst[i-1] + lst[i-2] + lst[i-3]
# print(res)

# 두번째 풀이

# 읽어오면서 계속 stack에 push 를 하다가 0이 발견되었을때 stack에 있는 내용들을 pop 을 3번

lst = list(map(int, input().split()))
sec_lst = []
res_lst = []
res = 0
for i in range(len(lst)):
    # 0이 아닐경우 sec_lst Stack에 push 한다
    if lst[i] != 0:
        sec_lst.append(lst[i])
        continue

    # push한 값이 0을 만났을때
    else:
        # 최근 3번 넣은 수 결과list에 추가
        res_lst.append(sec_lst.pop())
        res_lst.append(sec_lst.pop())
        res_lst.append(sec_lst.pop())
        break

print(sum(res_lst))