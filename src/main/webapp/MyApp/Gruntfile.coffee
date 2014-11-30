module.exports = (grunt) ->
	grunt.initConfig
		pkg: grunt.file.readJSON 'package.json'

		coffee:
			options:
					bare: true # Compile the JavaScript without the top-level function safety wrapper
			compile:
				expand: true,
				flatten: false, #here, it must be false to keep folder structure when compiling
				cwd: 'coffee',
				src: 'app/**/*.coffee',
				dest: '.',
				ext: '.js'

		clean:
			app:
				src: 'app/**/*.js'

		watch:
			coffee:
				files: ['coffee/app/**/*.coffee']
				tasks: ['clean:app','coffee:compile']

	grunt.loadNpmTasks 'grunt-contrib-coffee'
	grunt.loadNpmTasks 'grunt-contrib-watch'
	grunt.loadNpmTasks 'grunt-contrib-clean'

	grunt.registerTask 'default',['clean:app','coffee:compile','watch']