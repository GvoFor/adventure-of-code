import re
from functools import reduce

INPUT_PATH = '../inputs/day6.txt'

def operator_to_function(operator: str):
    if operator == '+':
        return lambda a, b: a + b
    return lambda a, b: a * b

def part1():
    SPACES_REGEX = re.compile(r'\s+')

    lines: list[str] = []
    with open(INPUT_PATH, 'r') as file:
        lines = file.read().split('\n')

    operators = SPACES_REGEX.split(lines[-1])
    operands = [[int(operand) for operand in SPACES_REGEX.split(line)] for line in lines[:-1]]

    results = [reduce(operator_to_function(operator), [operands[i][j] for i in range(len(operands))]) for j, operator in enumerate(operators)]

    grand_total = sum(results)

    print(f'Part 1 answer: {grand_total}')

def make_rectangular_with_extra_separator(table: list[str]):
    table_height = len(table)
    table_width = max([len(table[i]) for i in range(table_height)])
    for i in range(table_height):
        table[i] += ' ' * (1 + table_width - len(table[i]))

def is_separator_column(lines: list[str], column: int) -> bool:
    return column + 1 >= len(lines[-1]) or lines[-1][column + 1] != ' '

def solve_problem(lines: list[str], start_column: int, end_column: int) -> int:
    operands: list[int] = []
    for i in range(end_column - 1, start_column - 1, -1):
        operand = ''
        for y in range(len(lines)):
            if lines[y][i].isdigit():
                operand += lines[y][i]
        operands.append(int(operand))

    operator = lines[-1][start_column]
    return reduce(operator_to_function(operator), operands)

def part2():
    lines: list[str] = []
    with open(INPUT_PATH, 'r') as file:
        lines = file.read().split('\n')

    make_rectangular_with_extra_separator(lines)

    grand_total = 0

    problem_start = 0
    problem_end = 1
    while problem_start < len(lines[0]):
        while not is_separator_column(lines, problem_end):
            problem_end += 1

        grand_total += solve_problem(lines, problem_start, problem_end)

        problem_start = problem_end + 1
        problem_end = problem_start + 1

    print(f'Part 2 answer: {grand_total}')


if __name__ == '__main__':
    part1()
    part2()
