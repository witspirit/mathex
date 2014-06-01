var visuals = function() {

    var width = 200;
    var height = 200;

    var display = d3.select("#exDisplay")
        .attr("width", width).attr("height", height)
        .append("g")
        .attr("transform", "translate(10, "+height/2+")");

    var x = d3.scale.ordinal().rangeRoundBands([0, width], .1); // No domain yet, since the data length could be different

    var exerciseCounter = 0;

    var markError = function () {
        d3.select("#exDisplay").selectAll("text")
            .transition().duration(500)
            .style("fill", "red")
            .transition()
            .duration(1000)
            .delay(1000)
            .style("fill", "black");
    };

    var updateExercise = function(data) {
        x.domain(d3.range(0, data.length));

        // Going to use a trick with the key function to consider all new data as new
        var sumComponents = display.selectAll("text").data(data, function(d) { return exerciseCounter++; });

        // New elements
        sumComponents.enter().append("text")
            .attr("dy", ".35em");

        // Added + Updated elements
        sumComponents
            .attr("x", function(d, i) { return x(i); })
            .text(function(d) { return d; })
            .attr("y", -100)
            .style("fill-opacity", 1e-6)
            .style("fill", "black")
            .transition()
            .duration(750)
            .attr("y", 0)
            .style("fill-opacity", 1);


        // Removed elements
        sumComponents.exit()
            .transition()
            // Create a drop out, by moving them down on exit until we fully remove them
            .duration(750)
            .attr("y", 100)
            .style("fill-opacity", 1e-6)
            .remove();

    };


    return {
        markError : markError,
        updateExercise : updateExercise
    };
}();
