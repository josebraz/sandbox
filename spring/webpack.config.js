const HtmlWebPackPlugin = require("html-webpack-plugin");

module.exports = {
  entry: './src/main/js/Form.js',
  devtool: 'sourcemaps',
  cache: true,
  mode: 'development',
  output: {
      path: __dirname,
      filename: './src/main/resources/static/built/bundle.js'
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      },
      {
        test: /\.html$/,
        use: [
          {
            loader: "html-loader"
          }
        ]
      }
    ]
  }
};
