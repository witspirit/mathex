var mathexgen = function() {

    var sampleData = [
        {
            exercise : ["1", "+", "1"],
            solution : 2
        },
        {
            exercise : ["3", "-", "2"],
            solution : 1
        },
        {
            exercise : ["6", "+", "1", "+", "2"],
            solution : 9
        }
    ];

    var currentIndex = 0;
    var maxIndex = sampleData.length;

    var nextExercise = function() {
        currentIndex = (currentIndex + 1) % maxIndex;
        return sampleData[currentIndex];
    };

    return {
        next : nextExercise
    };
}();

