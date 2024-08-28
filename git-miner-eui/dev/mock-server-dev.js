const express = require("express");
const proxy = require("express-http-proxy");
const path = require("path");
const bodyParser = require("body-parser");

const euisdkRoutes = require("./euisdk-routes");

module.exports = (app) => {
  // const dummyda = {
  //   dummy: [
  //     {
  //       "Country": "United States",
  //       "Value": 12394
  //     },
  //     {
  //       "Country": "Russia",
  //       "Value": 6148
  //     },
  //     {
  //       "Country": "Germany (FRG)",
  //       "Value": 1653
  //     },
  //     {
  //       "Country": "France",
  //       "Value": 2162
  //     },
  //     {
  //       "Country": "United Kingdom",
  //       "Value": 1214
  //     },
  //     {
  //       "Country": "China",
  //       "Value": 1131
  //     },
  //     {
  //       "Country": "Spain",
  //       "Value": 814
  //     },
  //     {
  //       "Country": "Netherlands",
  //       "Value": 1167
  //     },
  //     {
  //       "Country": "Italy",
  //       "Value": 660
  //     },
  //     {
  //       "Country": "Israel",
  //       "Value": 1263
  //     }
  //   ]
  // };
  const obj = {
    columns: [
      { title: "Commit Hash", attribute: "col1", sortable: true },
      { title: "Date", attribute: "col2", sortable: true },
      { title: "Author", attribute: "col3", sortable: true },
      { title: "Message", attribute: "col4" },
      { title: "Add", attribute: "col5", sortable: true },
      { title: "Remove", attribute: "col6", sortable: true },
    ],
    data: [
      {
        col1: 0,
        col2: "24/10/2022",
        col3: "Mario",
        col4: "NO JIRA initializing python csv",
        col5: "+5",
        col6: "-3",
      },
      {
        col1: 1,
        col2: "19/10/2022",
        col3: "Marcin",
        col4: "NO JIRA initializing java spring boot",
        col5: "+12",
        col6: "-7",
      },
      {
        col1: 2,
        col2: "05/10/2022",
        col3: "James",
        col4: "NO JIRA initializing table",
        col5: "+11",
        col6: "-8",
      },
    ],
  };

  const obj2 = {
    columns: [
      { title: "Author", attribute: "col1", sortable: true },
      { title: "No. of Commits", attribute: "col2", sortable: true },
      { title: "Average", attribute: "col3", sortable: true },
      { title: "Percentage", attribute: "col4", sortable: true },
    ],

    data: [
      { col1: "Marcin", col2: 102, col3: 11.8, col4: "27" + "%" },
      { col1: "Mario", col2: 112, col3: 13.2, col4: "32" + "%" },
      { col1: "James", col2: 95, col3: 7.5, col4: "19" + "%" },
    ],
  };

  const obj4 = [
    {
      Word: "Max_Added",
      Value: 1500,
    },
    {
      Word: "Max_Removed",
      Value: 6148,
    },
    {
      Word: "Average_Added",
      Value: 1653,
    },
    {
      Word: "Average_Removed",
      Value: 2162,
    },
  ];
  const obj3 = {
    columns: [
      { title: "Hash", attribute: "col1", sortable: true },
      { title: "File", attribute: "col2", sortable: true },
      { title: "Max", attribute: "col3", sortable: true },
      { title: "Average", attribute: "col4", sortable: true },
      { title: "Add", attribute: "col5", sortable: true },
      { title: "Remove", attribute: "col6", sortable: true },
    ],

    data: [
      {
        col1: "Mgfhgfyueydggu678687t8756yu",
        col2: "ranFile1",
        col3: 11.8,
        col4: "27" + "%",
        col5: 635,
        col6: 201,
      },
      {
        col1: "gfhgfyueydggu678687t8756yu",
        col2: "ranFile12",
        col3: 13.2,
        col4: "32" + "%",
        col5: 55,
        col6: 6,
      },
      {
        col1: "gfhgfyueydggu678687t8756yu",
        col2: "ranFile123",
        col3: 7.5,
        col4: "19" + "%",
        col5: 125,
        col6: 32,
      },
    ],
  };
  //Get Commit live data
  app.get("/api/getCommitTable", function (req, res) {
    res.setHeader("Content-Type", "application/json");
    res.send(JSON.stringify(obj));
  });

  //Get Repo live data
  app.get("/api/getRepoTable", function (req, res) {
    res.setHeader("Content-Type", "application/json");
    res.send(JSON.stringify(obj2));
  });

  //Get Hash live data
  app.get("/api/getCommitInfo/:hashId", function (req, res) {
    res.setHeader("Content-Type", "application/json");
    res.send(JSON.stringify(obj3));
  });

  //http://localhost:8080/api/getInformationAboutRepo
  //make obj4
  //Get Bar Chart live data
  app.get("/api/getInformationAboutRepo/barchart", function (req, res) {
    res.setHeader("Content-Type", "application/json");
    res.send(JSON.stringify(obj4));
  });

  //Get Hunks Count live data
  // app.get("/api/getHunksCountTable", function (req, res) {
  //   res.setHeader("Content-Type", "application/json");
  //   res.send(JSON.stringify(obj));
  // });
  /**
   * Webpack uses express 4.x. express.BodyParser() no longer exists. Use body-parser. Required for developing advanced mock Rest services.
   */
  app.use(bodyParser.json());

  /**
   * Initialize E-UI SDK routes
   */
  euisdkRoutes.init(app);

  // ------------------------------------------------------------
  // Custom Routes
  // ------------------------------------------------------------

  // ------------------------------------------------------------
  // Mocking server responses...
  // ------------------------------------------------------------

  /**
   * List all routes that should be proxied here.
   *
   * A route definition must be defined as follows:
   * { path: <path (path can be a regular expression)> }
   *
   * i.e.
   * const routes = [{ path: '/my/first/path' }]
   *
   * See documentation for further details
   *
   * http://presentation-layer.lmera.ericsson.se/euisdkdocs/#docs?chapter=tools&section=proxy
   */
  const routes = [];

  /**
   * Proxy all routes via the server specified in the start script.
   *
   * To start the project with a proxy setup to handle routes from
   * the "routes" array the following command is used:
   *
   * npm start -- --server=<path-to-you-application-server>
   *
   * eg.
   * npm start -- --server=http://localhost:3000
   *
   * Only use a proxy for the paths listed in the routes array.
   */
  process.argv.forEach((arg) => {
    if (arg.startsWith("--server")) {
      const _proxy = arg.substring(arg.indexOf("=") + 1);
      app.use(
        "/",
        proxy(_proxy, {
          filter: (req) =>
            routes.some((_route) => RegExp(_route.path).test(req.path)),
        })
      );
    }
  });
};
