var app = angular.module("humbak", []);
app.controller("tibiatya.itallap.controller",
        function ($scope, $location, $timeout) {

            $scope.tibimodel = {};

            $scope.doneLoading = false;

            $scope.loadEnded = function () {
                $scope.doneLoading = true;
                $scope.$apply();
            };

            $scope.getDrinks = function () {
                $scope.doneLoading = false;
                $.ajax({
                    url: '/humbak/rest/drink/all',
                    type: 'GET',
                    async: true,
                    contentType: 'application/json',
                    error: function (error) {
                        alert(error.statusText);
                        $timeout($scope.loadEnded());
                    },
                    success: function (result) {
                        if (result) {
                            $scope.tibimodel.beers = result.beers;
                            $scope.tibimodel.spirits = result.spirits;
                            $scope.tibimodel.wines = result.wines;
                            $timeout($scope.loadEnded());
                        }
                    }
                });
            };

            $scope.getDrinks();
        });

app.controller("tibiatya.manager.controller",
        function ($scope, $location, $timeout) {

            $scope.tibimodel = {};

            $scope.doneLoading = false;

            $scope.loadEnded = function () {
                $scope.doneLoading = true;
                $scope.$apply();
            };

            //$scope.tibimodel.reservations = {};

            $scope.getReservationsByDate = function () {
                $scope.doneLoading = false;
                $.ajax({
                    url: '/humbak/rest/reservation/listReservations',
                    type: 'GET',
                    async: true,
                    data: {'date': $scope.chosendate},
                    error: function (error) {
                        alert(error.statusText);
                        $timeout($scope.loadEnded());
                    },
                    success: function (result) {
                        if (result) {
                            $scope.tibimodel.reservations = result;
                            $timeout($scope.loadEnded());
                        }
                    }
                });
            };

            $scope.chosendate = Date.now();

            $scope.getReservationsByDate();
        });

app.controller("tibiatya.pubtable.controller",
        function ($scope, $location, $timeout) {

            $scope.tibimodel = {};

            $scope.doneLoading = false;

            $scope.loadEnded = function () {
                $scope.doneLoading = true;
                $scope.$apply();
            };

            //$scope.tibimodel.reservations = {};

            $scope.getReservationsByDate = function () {
                $scope.doneLoading = false;
                $.ajax({
                    url: '/humbak/rest/table/free',
                    type: 'GET',
                    async: true,
                    data:{'data': $scope.chosendate},
                    contentType: 'application/json',
                    error: function (error) {
                        alert(error.statusText);
                        $timeout($scope.loadEnded());
                    },
                    success: function (result) {
                        if (result) {
                            $scope.tibimodel.pubtables = result;
                            $timeout($scope.loadEnded());
                        }
                    }
                });
            };

            $scope.chosendate = Date.now();

            $scope.getReservationsByDate();
        });

app.controller("tibiatya.user.controller",
        function ($scope, $location, $timeout) {

            $scope.tibimodel = {};

            $scope.doneLoading = false;

            $scope.loadEnded = function () {
                $scope.doneLoading = true;
                $scope.$apply();
            };

            $scope.getUser = function () {
                $scope.doneLoading = false;
                $.ajax({
                    url: '/humbak/rest/user/whoisloggedin',
                    type: 'GET',
                    async: true,
                    contentType: 'application/json',
                    error: function (error) {
                        alert(error.statusText);
                        $timeout($scope.loadEnded());
                    },
                    success: function (result) {
                        if (result) {
                            $scope.user = result;
                            $timeout($scope.loadEnded());
                        }
                    }
                });
            };
            
            $scope.getUser();
        });
        