INPUT_PATH = '../inputs/day1.txt'

INITIAL_VALUE = 50
TOTAL_VALUES = 100
TARGET_VALUE = 0

def part1():
    current = INITIAL_VALUE
    target_counter = 0

    with open(INPUT_PATH, 'r') as file:
        for line in file:
            line = line.strip()
            direction = 1 if line[0] == 'R' else -1
            offset = int(line[1:]) * direction

            current = (current + offset) % TOTAL_VALUES
            if current == TARGET_VALUE:
                target_counter += 1

    print(f'Target counter: {target_counter}')

def part2():
    def count_targets_in_range(a: int, b: int, is_turning_right: bool) -> int:
        # the code bellow is equivalent to
        # return len(list(filter(lambda x: x % TOTAL_VALUES == TARGET_VALUE, range(a, b+1))))
        if is_turning_right:
            a += (TARGET_VALUE - a) % TOTAL_VALUES
        else:
            b -= (b - TARGET_VALUE) % TOTAL_VALUES
        if a > b:
            return 0
        return 1 + (b - a) // TOTAL_VALUES

    current = INITIAL_VALUE
    target_counter = 0

    with open(INPUT_PATH, 'r') as file:
        for line in file:
            line = line.strip()
            direction = 1 if line[0] == 'R' else -1
            offset = int(line[1:]) * direction

            raw_next = current + offset
            is_turning_right = direction > 0
            a = current + 1 if is_turning_right else raw_next
            b = raw_next if is_turning_right else current - 1
            target_counter += count_targets_in_range(a, b, is_turning_right)

            current = raw_next % TOTAL_VALUES

    print(f'Target counter: {target_counter}')


if __name__ == '__main__':
    part1()
    part2()