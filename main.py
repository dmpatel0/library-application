from website import create_app

# Create the actual application
app = create_app()

# Make sure the to only run code from python interpreter
if __name__ == '__main__':
    app.run(debug=True)