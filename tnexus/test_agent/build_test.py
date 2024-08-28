import time


class TestAgent:
    def __init__(self):
        self.steps = []

    def append(self, step):
        self.steps.append(step)

    def get_steps(self):
        return self.steps

    def execute(self):
        print('EXECUTION OF TEST CASE WILL BEING IN 20 seconds')
        time.sleep(10)
        print('EXECUTING STEPS')
        while len(self.steps) > 0:
            step = self.steps.pop(0)
            step()
            time.sleep(0.1)