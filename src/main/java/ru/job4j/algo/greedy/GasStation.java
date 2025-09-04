package ru.job4j.algo.greedy;

class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int tank = 0;
        int start = 0;

        for (int index = 0; index < gas.length; index++) {
            totalGas += gas[index];
            totalCost += cost[index];
            tank += gas[index] - cost[index];
            if (tank < 0) {
                start = index + 1;
                tank = 0;
            }
        }
        return totalGas < totalCost ? -1 : start;
    }
}
