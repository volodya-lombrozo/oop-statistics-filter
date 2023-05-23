# Statistics of application methods profiling

Here is the latest statics of application methods:

|Application            |Total Methods|Static Methods|Instance Methods|Constructors|Not Found Methods (lambdas)|Total Time (ms)|Time in Static Methods (ms)|Time in Intense Methods (ms)|Time in Constructors Methods (ms)|Percent of Static Methods|Percent of Instance Methods|Percent of Constructors|Percent Not Found Methods|Percent Dirty Static Methods|Percent Dirty Instance Methods|
|-----------------------|-------------|--------------|----------------|------------|---------------------------|---------------|---------------------------|----------------------------|---------------------------------|-------------------------|---------------------------|-----------------------|-------------------------|----------------------------|------------------------------|
|Apache Derby           |33362560     |852885        |31858497        |117895      |533283                     |164890.0       |3203.9                     |158729.9                    |587.8                            |2.56                     |95.49                      |0.35                   |1.60                     |4.15                        |97.09                         |
|Apache Kafka           |148029       |8107          |58994           |16902       |64026                      |1209.5         |64.1                       |328.9                       |75.4                             |5.48                     |39.85                      |11.42                  |43.25                    |48.73                       |83.11                         |
|Apache Tomcat          |529440       |36674         |417282          |5907        |69577                      |2994.4         |455.4                      |2025.6                      |30.3                             |6.93                     |78.82                      |1.12                   |13.14                    |20.07                       |91.96                         |
|Spring Framework 5.3.27|421179       |45361         |263994          |40378       |71446                      |3729.2         |327.0                      |2368.0                      |329.0                            |10.77                    |62.68                      |9.59                   |16.96                    |27.73                       |79.64                         |
|Takes Framework        |234240       |80628         |35557           |101358      |16697                      |3147.7         |1354.0                     |175.8                       |309.9                            |34.42                    |15.18                      |43.27                  |7.13                     |41.55                       |22.31                         |

Where

- **Application** - the name of the tested application
- **Total Methods** - total number of methods that were counted during
  profiling (
  including lambdas, constructors, static/instance methods)
- **Static Methods** - total number of static methods that were counted during
  profiling
- **Instance Methods** - total number of instance methods that were counted
  during profiling
- **Constructors** - total number of constructors that were counted during
  profiling
- **Not Found Methods (lambdas)** - total number of methods that were not found
  in the source code, or labdas, or methods in the inner classes
- **Total Time (ms)** - total time spent in all methods
- **Time in Static Methods (ms)** - total time spent in static methods
- **Time in Intense Methods (ms)** - total time spent in instance methods
- **Time in Constructors Methods (ms)** - total time spent in constructors
- **Percent of Static Methods** - percent of time spent in static methods (
  Static Methods / Total Methods)
- **Percent of Instance Methods** - percent of time spent in instance methods (
  Instance Methods / Total Methods)
- **Percent of Constructors** - percent of time spent in constructors (
  Constructors / Total Methods)
- **Percent Not Found Methods** - percent of time spent in not found methods (
  Not Found Methods / Total Methods)
- **Percent Dirty Static Methods** - percent of time spent in static methods plus not found methods ( (Static Methods + Not Found Methods) / Total Methods)
- **Percent Dirty Instance Methods** - percent of time spent in instance methods plus not found methods ( (Instance Methods + Not Found Methods) / Total Methods)

The CSV file with final statics is [here](report.csv).

The raw profiling results by methods you can
get [here](https://github.com/volodya-lombrozo/cost-of-oop/tree/main/results)


