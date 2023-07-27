from flask import Flask

# Creating the application
# Uses blueprints from other files and registers them to the application for viewing
def create_app():
    app = Flask(__name__)
    app.config['SECRET_KEY'] = 'fj4&*34Ff*#$3fhDFh8*'

    from .views import views
    from .auth import auth

    app.register_blueprint(views, url_prefix='/')
    app.register_blueprint(auth, url_prefix='/')

    return app


