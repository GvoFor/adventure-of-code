INPUT_PATH = '../inputs/day4.txt'

MAX_NEIGHBOURS_TO_ACCESS = 4

def is_roll(bitmap: list[list[int]], i: int, j: int) -> bool:
    return bitmap[i][j] == 1

def count_neighbours(bitmap: list[list[int]], i: int, j: int) -> int:
    ys = [y for y in (i - 1, i, i + 1) if (0 <= y < len(bitmap))]
    xs = [x for x in (j - 1, j, j + 1) if (0 <= x < len(bitmap))]
    neighbors = 0
    for y in ys:
        for x in xs:
            if is_roll(bitmap, y, x):
                neighbors += 1
    return neighbors - 1 if bitmap[i][j] == 1 else neighbors

def can_roll_be_accessed(bitmap: list[list[int]], i: int, j: int) -> bool:
    return count_neighbours(bitmap, i, j) < MAX_NEIGHBOURS_TO_ACCESS

def get_input_paper_roll_bitmap() -> list[list[int]]:
    paper_roll_bitmap: list[list[int]] = []
    with open(INPUT_PATH, 'r') as file:
        for line in file:
            paper_roll_bitmap.append(
                list(map(lambda ch: 1 if ch == '@' else 0, list(line.strip())))
            )
    return paper_roll_bitmap

def part1():
    paper_roll_bitmap = get_input_paper_roll_bitmap()

    rolls_with_access = 0
    for i in range(len(paper_roll_bitmap)):
        for j in range(len(paper_roll_bitmap)):
            if paper_roll_bitmap[i][j] == 0:
                continue
            if can_roll_be_accessed(paper_roll_bitmap, i, j):
                rolls_with_access += 1

    print(f'Number of rolls that can be accessed: {rolls_with_access}')

def part2():
    paper_roll_bitmap = get_input_paper_roll_bitmap()

    rolls_removed = 0
    at_least_one_was_removed = True
    while at_least_one_was_removed:
        at_least_one_was_removed = False
        for i in range(len(paper_roll_bitmap)):
            for j in range(len(paper_roll_bitmap)):
                if not is_roll(paper_roll_bitmap, i, j):
                    continue
                if can_roll_be_accessed(paper_roll_bitmap, i, j):
                    rolls_removed += 1
                    paper_roll_bitmap[i][j] = 0
                    at_least_one_was_removed = True

    print(f'Number of rolls that can be removed: {rolls_removed}')


if __name__ == '__main__':
    part1()
    part2()
