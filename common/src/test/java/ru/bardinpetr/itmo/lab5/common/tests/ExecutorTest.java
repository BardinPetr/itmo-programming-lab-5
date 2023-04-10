package ru.bardinpetr.itmo.lab5.common.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bardinpetr.itmo.lab5.common.executor.Executor;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserPromptedAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutorTest {

    @Test
    @DisplayName("Executor should call handlers for registered commands")
    void executorRegistrationTest() {
        var exec = new Executor();

        var val_req = "test";
        var val_res = "test2";
        exec.registerOperation(
                TestCmd.class,
                req -> {
                    assertEquals(
                            TestCmd.class, req.getClass(),
                            "Request in handler should be typed"
                    );
                    assertEquals(
                            val_req, req.value,
                            "Handler should receive arguments of request"
                    );
                    var resp = req.createResponse();
                    assertEquals(
                            TestCmd.TestCmdResponse.class, resp.getClass(),
                            "Autogenerated response object should be of class defined in request"
                    );
                    resp.value = val_res;
                    return resp;
                }
        );

        var resp = exec.execute(new TestCmd(val_req));
        assertTrue(resp.isSuccess());

        assertEquals(
                TestCmd.TestCmdResponse.class, resp.getClass(),
                "Response should be of class declared in request command"
        );

        var casted = (TestCmd.TestCmdResponse) resp;
        assertEquals(
                val_res, casted.value,
                "Response should contain data sent from executor"
        );
    }

    @Test
    @DisplayName("Executor should support void operations")
    void executorVoidTest() {
        var exec = new Executor();

        var val_req = "test";
        exec.registerVoidOperation(
                TestCmd.class,
                req -> {
                    assertEquals(
                            TestCmd.class, req.getClass(),
                            "Request in handler should be typed"
                    );
                    assertEquals(
                            val_req, req.value,
                            "Handler should receive arguments of request"
                    );
                }
        );

        var resp = exec.execute(new TestCmd(val_req));
        assertTrue(resp.isSuccess());

        assertEquals(
                TestCmd.TestCmdResponse.class, resp.getClass(),
                "Response should be of class declared in request command"
        );
    }

    @Test
    @DisplayName("Exceptions thrown in handler should be turned into error-response")
    void executorErrorTest() {
        var exception = new RuntimeException("Test exception");

        var exec = new Executor();
        exec.registerOperation(TestCmd2.class,
                (req) -> {
                    throw exception;
                }
        );

        var resp = exec.execute(new TestCmd2());
        assertFalse(resp.isSuccess(), "Response should have error mark");
        assertEquals(exception.getMessage(), resp.getTextualResponse(), "Error text should be in message");
    }

    @Test
    @DisplayName("Nested executors should properly handle infinite recursion")
    void executorRecursionTest() {
        var exec = new Executor();
        var exec2 = new Executor();
        exec.registerExecutor(exec2);
        exec2.registerExecutor(exec);

        assertDoesNotThrow(() -> {
            var res = exec.execute(new TestCmd2());

            assertFalse(res.isResolved(), "command should not be resolved if recursion exists");
        });
    }

    @Test
    @DisplayName("Nested executors should properly pass unresolved")
    void executorDelegationTest() {
        var exec = new Executor();
        var exec2 = new Executor();
        exec.registerExecutor(exec2);
        exec2.registerExecutor(exec);

        var val_req = "test";
        var val_res = "test2";
        exec2.registerOperation(
                TestCmd.class,
                req -> {
                    assertEquals(
                            TestCmd.class, req.getClass(),
                            "Request in handler should be typed"
                    );
                    assertEquals(
                            val_req, req.value,
                            "Handler should receive arguments of request"
                    );
                    var resp = req.createResponse();
                    assertEquals(
                            TestCmd.TestCmdResponse.class, resp.getClass(),
                            "Autogenerated response object should be of class defined in request"
                    );
                    resp.value = val_res;
                    return resp;
                }
        );

        // response validation
        var resp = exec.execute(new TestCmd(val_req));
        assertTrue(resp.isResolved(), "Command handler should be recursively resolved");
        assertTrue(resp.isSuccess());

        assertEquals(
                TestCmd.TestCmdResponse.class, resp.getClass(),
                "Response should be of class declared in request command"
        );

        var casted = (TestCmd.TestCmdResponse) resp;
        assertEquals(
                val_res, casted.value,
                "Response should contain data sent from executor"
        );


        // Order validation
        exec.registerVoidOperation(TestCmd2.class, (req) -> {
        });
        exec2.registerVoidOperation(TestCmd2.class, (req) -> {
            fail("Handler resolution prefer local implementations than recurse");
        });
        exec.execute(new TestCmd2());
    }

    static class TestCmd extends APICommand implements UserPromptedAPICommand {
        public String value;

        public TestCmd(String value) {
            this.value = value;
        }

        @Override
        public String getType() {
            return "test";
        }

        @Override
        public TestCmdResponse createResponse() {
            return new TestCmdResponse();
        }

        public static class TestCmdResponse extends APICommandResponse {
            public String value;
        }
    }


    static class TestCmd2 extends APICommand implements UserPromptedAPICommand {
        @Override
        public String getType() {
            return "test2";
        }
    }
}
