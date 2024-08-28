'''
from pynput import mouse
from extension_decorator import extension
from test_agent.test_agent_decorators.record_steps_decorator import record_steps
from test_agent.build_test import TestAgent

test_agent = TestAgent()
mouse_controller = mouse.Controller()
button_left = mouse.Button.left


@record_steps(test_agent, mouse_controller.click, 0.01)
def mouse_button_press(event):
    return [mouse.Button.left]


def change_mouse_position(x, y):
    global mouse_controller
    mouse_controller.position = (x, y)


@record_steps(test_agent, lambda x, y: change_mouse_position(x, y), 0.01)
def mouse_movement(event):
    x, y = event
    return x, y


def mouse_extension():
    with mouse.Events() as events:
        event = events.get(0.5)
        if isinstance(event, mouse.Events.Move):
            mouse_movement((event.x, event.y))
        event = events.get(0.5)
        if isinstance(event, mouse.Events.Click):
            mouse_button_press(event.button.left)
'''