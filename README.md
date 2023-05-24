# Data generator API
This repository contains REST API for [Performance Data Generator](https://github.com/Debezium-performance/performace-data-generator).
This API uses Performance Data Generator to generate custom random data, and send these towards [DMT](https://github.com/Debezium-performance/database-manipulation-tool)
in some of the implemented scenarios.

Scenario in this case means pattern that will be followed by HTTP client during execution.
As example if you select constant load generator will send same amount of data in each `rate` step.

## Requirements
- Java 17
- Maven >= 3.9.1
- Podman

## API endpoints

- `/person/constant?requestCount=<value>&maxRowCount=<value>&rate=<>&rounds=<>`
- `/person/linear?requestCount=<value>&maxRowCount=<value>&rate=<>&delta=<>`
- `/person/peak?requestCount=<value>&maxRowCount=<value>&rate=<>&peakLevel=<>&peakRounds=<>&quietRounds=<>`


## Deployment and Usage
TBD

## Release
TBD

## Related afforts
- [TEALC](https://github.com/ExcelentProject) - Tooling that allows to maintain long running test environment

## Contributing
TBD

### Contributors

This project exists thanks to all the people who contribute.
<a href="https://github.com/Debezium-performance/data-generator-api"><img src="https://github.com/Debezium-performance/data-generator-api/contributors.svg?width=890&button=false" /></a>

## License
[MIT](LICENSE) © Ondrej Babec