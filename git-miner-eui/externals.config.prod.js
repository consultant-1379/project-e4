const externals = {
  apps: [{
    path: "app-1",
    entry: "App1"
  }],
  components: {
    default: [],
    shareable: [{
      path: "my-table",
      entry: "MyTable"
    }, {
      path: "bar-chart",
      entry: "BarChart"
    }]
  },
  panels: [],
  plugins: []
};
module.exports = externals;