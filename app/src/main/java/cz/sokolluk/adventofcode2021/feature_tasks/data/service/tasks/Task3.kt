package cz.sokolluk.adventofcode2021.feature_tasks.data.service.tasks

import cz.sokolluk.adventofcode2021.feature_tasks.data.service.Task

class Task3: Task {
    override suspend fun computeFirstPart(input: String): String? {

        val data = input.split("\n")

        val dataSize = data[0].length

        val count1bits = ArrayList<Int>((0 until dataSize).map{ 0 })

        data.forEach { bits ->
            count1bits.forEachIndexed { i, value ->
                if (bits[i] == '1') count1bits[i] = value + 1
            }
        }

        var gamma = ""
        var epsilon = ""
        count1bits.forEach { count ->
            if (count > (data.size/2)){
                gamma += "1"
                epsilon += "0"
            } else {
                gamma += "0"
                epsilon += "1"
            }
        }

        val gammaInt = Integer.parseInt(gamma, 2)
        val epsilonInt = Integer.parseInt(epsilon, 2)

        return (gammaInt * epsilonInt).toString()
    }

    override suspend fun computeSecondPart(input: String): String? {
        val data = input.split("\n")
        var oGeRa = ArrayList(data)
        var Co2ScRa = ArrayList(data)
        val dataSize = data[0].length

        var currentIdx = 0

        while(oGeRa.size > 1 && currentIdx < dataSize) {
            val ones = arrayListOf<String>()
            val zeros = arrayListOf<String>()
            oGeRa.forEach { line ->
                if (line[currentIdx] == '1')
                    ones.add(line)
                else
                    zeros.add(line)
            }
            oGeRa = if (ones.size >= zeros.size) ones else zeros
            currentIdx++
        }
        currentIdx = 0
        while(Co2ScRa.size > 1 && currentIdx < dataSize) {
            val ones = arrayListOf<String>()
            val zeros = arrayListOf<String>()
            Co2ScRa.forEach { line ->
                if (line[currentIdx] == '1')
                    ones.add(line)
                else
                    zeros.add(line)
            }
            Co2ScRa = if (zeros.size <= ones.size) zeros else ones
            currentIdx++
        }

        val oGeRaInt = Integer.parseInt(oGeRa[0], 2)
        val Co2ScRaInt = Integer.parseInt(Co2ScRa[0], 2)

        return (oGeRaInt * Co2ScRaInt).toString()
    }


}