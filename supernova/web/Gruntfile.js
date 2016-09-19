/*global module, require*/
module.exports = function (grunt) {
    'use strict';
    var globalConfig = {
            //set this to the value of the pom xml file
            //future state this will be read directly from the pom.xml file
            pom: '1.0.0'
        }, jsModuleFolder = '',
        globalDependencyFiles = [
        ];

    // Project configuration.
    grunt.initConfig({
        globalConfig: globalConfig,
        pkg: grunt.file.readJSON('package.json'),
        watch: {
            css: {
                files: ['src/main/webapp/sass/**/*.scss'],
                tasks: ['scsslint', 'compass']
            },
            js: {
                //Added build folder exclusion for for Java 1.7 / Tomcat 7 migration
                files: [jsModuleFolder, '!src/main/webapp/js/base/bcusMod/build/**'],
                tasks: ['jshint:all', 'requirejs:dev']
            },
            jasmine: {
                files: ['src/main/webapp/js/base/component/**/*.js'],
                tasks: 'jasmine:test'
            },
            images: {
                files: ['src/main/webapp/img/**/*.{png,jpg,gif}'],
                tasks: ['newer:imagemin:dynamic']
            },
            handlebars: {
                files: ["src/main/webapp/js/base/bcusMod/components/**/*.hbs",
                    "src/main/webapp/js/base/bcusMod/pages/**/*.hbs"],
                tasks: ["handlebars:compile"]
            }
        },
        remove: {
            options: {
                trace: true
            },
            fileList: ['src/main/webapp/build/js/*.js'],
            dirList: ['src/main/webapp/build/js']
        },
        ts: {
            compileFileSet: {
                // This will run tsc twice.  The first time, the result of the 'files1/**/*.ts' glob will be
                // passed to tsc with the --out switch as 'out/ArrayStyle/1.js'.
                // see https://github.com/gruntjs/grunt-docs/blob/master/Configuring-tasks.md#files-array-format
                files: [
                    { src: ['src/main/ts/**/*.ts'], dest: ['src/main/webapp/build/js'] }
                ],
                options: {
                    fast: 'never',
                    target: 'es5',
                    verbose: 'true',
                    emitDecoratorMetadata: 'true',
                    module: 'commonjs',
                    sourceMap: false,
                    failOnTypeErrors: true,
                    "moduleResolution": "node",
                    "experimentalDecorators": true,
                    "removeComments": false,
                    "noImplicitAny": true,
                    "suppressImplicitAnyIndexErrors": true
                }
            }
        },
        tslint: {
            options: {
                // can be a configuration object or a filepath to tslint.json
                configuration: "tslint.json",
                formatter: "prose",
                outputFile: "violations.txt",
                appendToOutput: true
            }
        },
        copy: {
            main: {
                expand: true,
                cwd: 'src/main/ts/',
                src: ['**/*.css'],
                dest: 'src/main/webapp/build/js/'
            }
        },
        exec: {
            clearcachemsg: {
                command: 'echo "Clearing google chrome cache. Please close chrome in case of access denied issue."',
                stdout: true,
                stderr: true
            },
            clearchromecache: {
                command: 'del /q /s /f "%LOCALAPPDATA%/Google/Chrome/User Data/Default/Cache"',
                stdout: true,
                stderr: true
            }
        }
    });
    grunt.loadNpmTasks('grunt-exec');
    grunt.loadNpmTasks('grunt-remove');
    grunt.loadNpmTasks('grunt-ts');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-tslint');
    grunt.registerTask('dev', ['watch']);
    grunt.registerTask('build', ['remove', 'ts']);
    grunt.registerTask('qb', ['ts']);
    grunt.registerTask('build-clearcache', ['exec']);
    grunt.registerTask('default', ['build']);
    grunt.registerTask('analyze', ['tslint']);
};