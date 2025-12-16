# Brute-force solution. Very unefficient, but enough to solve the problem in few seconds

INPUT_PATH = '../inputs/day2.txt'

def part1():
    def is_id_invalid(id: int) -> bool:
        id = str(id)
        length = len(id)
        if length % 2 == 1:
            return False
        half_len = length // 2
        return id[:half_len] == id[half_len:]

    def get_all_invalid_ids_in_range(start: int, end: int) -> list[int]:
        invalid_ids = list(filter(is_id_invalid, range(start, end + 1)))
        return invalid_ids

    total_sum = 0
    with open(INPUT_PATH, 'r') as file:
        ranges = file.readline().strip().split(',')
        for r in ranges:
            start, end = r.split('-')
            invalid_ids = get_all_invalid_ids_in_range(int(start), int(end))
            total_sum += sum(invalid_ids)

    print(f'Total sum: {total_sum}')

def part2():
    def is_id_invalid(id: int) -> bool:
        id = str(id)

        length = len(id)
        half_len = length // 2
        l = half_len
        while l > 0:
            if (length // l) != (length / l):
                l -= 1
                continue
            invalid = True
            for i in range(length // l - 1):
                a = (i + 0) * l
                b = (i + 1) * l
                c = (i + 2) * l
                if id[a:b] != id[b:c]:
                    invalid = False
                    break
            if invalid:
                return True
            l -= 1
        return False

    def get_all_invalid_ids_in_range(start: int, end: int) -> list[int]:
        invalid_ids = list(filter(is_id_invalid, range(start, end + 1)))
        return invalid_ids

    total_sum = 0
    with open(INPUT_PATH, 'r') as file:
        ranges = file.readline().strip().split(',')
        for r in ranges:
            start, end = r.split('-')
            invalid_ids = get_all_invalid_ids_in_range(int(start), int(end))
            total_sum += sum(invalid_ids)

    print(f'Total sum: {total_sum}')


if __name__ == '__main__':
    part1()
    part2()