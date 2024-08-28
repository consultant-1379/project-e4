import time

from extension_decorator import extension


@extension('terminal')
def terminal_extension(key, value):
    print('This is the terminal extension')
    time.sleep(1)
