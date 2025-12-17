INPUT_PATH = '../inputs/day5.txt'

def get_input() -> tuple[list[tuple[int, int]], list[int]]:
    ids_ranges: list[tuple[int, int]] = []
    ids_to_check: list[int] = []
    with open(INPUT_PATH, 'r') as file:
        for line in file:
            if line.strip() == '':
                break
            start, end = map(int, line.strip().split('-'))
            ids_ranges.append((start, end))
        for line in file:
            ids_to_check.append(int(line.strip()))
    return ids_ranges, ids_to_check

def unify_unsorted_ranges(ranges: list[tuple[int, int]]) -> list[tuple[int, int]]:
    sorted_ranges = sorted(ranges, key=lambda x: x[0])
    unified_ranges: list[tuple[int, int]] = []
    a, b = sorted_ranges[0]
    for next_a, next_b in sorted_ranges[1:]:
        if next_a <= b:
            b = max(b, next_b)
            continue
        unified_ranges.append((a, b))
        a, b = next_a, next_b
    unified_ranges.append((a, b))
    return unified_ranges

def is_in_range(x: int, ranges: list[tuple[int, int]]) -> bool:
    for a, b in ranges:
        if a <= x <= b:
            return True
    return False

def part1():
    ids_ranges, ids_to_check = get_input()

    sorted_unified_ranges = unify_unsorted_ranges(ids_ranges)

    fresh_food_counter = 0
    for food_id in ids_to_check:
        if is_in_range(food_id, sorted_unified_ranges):
            fresh_food_counter += 1

    print(f'Part 1 answer: {fresh_food_counter}')

def part2():
    ids_ranges, _ = get_input()

    sorted_unified_ranges = unify_unsorted_ranges(ids_ranges)

    fresh_food_counter = 0
    for (a, b) in sorted_unified_ranges:
        fresh_food_counter += b - a + 1

    print(f'Part 2 answer: {fresh_food_counter}')


if __name__ == '__main__':
    part1()
    part2()
