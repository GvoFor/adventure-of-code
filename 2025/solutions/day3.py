INPUT_PATH = '../inputs/day3.txt'

def part1():
    def get_bank_voltage(bank: list[int]) -> int:
        if len(bank) < 2:
            return 0

        first_digit = 0
        first_digit_i = -1
        for i in range(len(bank) - 1):
            if bank[i] > first_digit:
                first_digit = bank[i]
                first_digit_i = i

        second_digit = 0
        for i in range(first_digit_i + 1, len(bank)):
            if bank[i] > second_digit:
                second_digit = bank[i]

        return 10 * first_digit + second_digit


    total_sum = 0
    with open(INPUT_PATH, 'r') as file:
        for line in file:
            bank = list(map(int, list(line.strip())))
            total_sum += get_bank_voltage(bank)

    print(f'Total sum: {total_sum}')

def part2():
    BATTERIES_TO_TURN_ON = 12

    def get_bank_voltage(bank: list[int]) -> int:
        batteries_in_bank = len(bank)
        if batteries_in_bank < BATTERIES_TO_TURN_ON:
            return 0

        digits = [0 for _ in range(BATTERIES_TO_TURN_ON)]
        last_picked_i = -1
        for digit_i in range(BATTERIES_TO_TURN_ON):
            for i in range(last_picked_i + 1, batteries_in_bank - BATTERIES_TO_TURN_ON + 1 + digit_i):
                if bank[i] > digits[digit_i]:
                    digits[digit_i] = bank[i]
                    last_picked_i = i

        bank_voltage = 0
        for i in range(BATTERIES_TO_TURN_ON):
            bank_voltage += 10 ** (BATTERIES_TO_TURN_ON - 1 - i) * digits[i]

        return bank_voltage

    total_sum = 0
    with open(INPUT_PATH, 'r') as file:
        for line in file:
            bank = list(map(int, list(line.strip())))
            total_sum += get_bank_voltage(bank)

    print(f'Total sum: {total_sum}')


if __name__ == '__main__':
    part1()
    part2()