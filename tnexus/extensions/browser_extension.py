import time
from extension_decorator import extension, pre_load
from selenium import webdriver
import time
from test_agent.build_test import TestAgent

driver = ''
test_agent = TestAgent()


def load_in_driver(key, value):
    global driver
    print(key, value)
    launch_browser = value.title()
    driver = getattr(webdriver, launch_browser)()


@pre_load(load_in_driver)
@extension('browser')
def browser_extension(key, value):
    print(key, value)
    global driver
    print('This is the browser extension')
    print(driver.switch_to.active_element)
    time.sleep(1)
