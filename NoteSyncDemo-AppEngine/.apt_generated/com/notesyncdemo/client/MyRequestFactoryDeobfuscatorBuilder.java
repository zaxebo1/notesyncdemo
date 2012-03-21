// Automatically Generated -- DO NOT EDIT
// com.notesyncdemo.client.MyRequestFactory
package com.notesyncdemo.client;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
public final class MyRequestFactoryDeobfuscatorBuilder extends com.google.web.bindery.requestfactory.vm.impl.Deobfuscator.Builder {
{
withOperation(new OperationKey("Iu0$w_4a3tQPOByldeGGrRWISkk="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()Ljava/lang/String;")
  .withMethodName("send")
  .withRequestContext("com.notesyncdemo.client.MyRequestFactory$MessageRequest")
  .build());
withOperation(new OperationKey("SZYxxtttXzQLTsjI6KiXrCxWDds="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/lang/String;")
  .withMethodName("getMessage")
  .withRequestContext("com.notesyncdemo.client.MyRequestFactory$HelloWorldRequest")
  .build());
withOperation(new OperationKey("rUD6FKIDr4JCBNKQ5zZY1VrvFE8="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()V")
  .withMethodName("register")
  .withRequestContext("com.notesyncdemo.client.MyRequestFactory$RegistrationInfoRequest")
  .build());
withOperation(new OperationKey("CL7OO5RFY$JMh3MJvwb$TwjYu4k="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/InstanceRequest;")
  .withDomainMethodDescriptor("()V")
  .withMethodName("unregister")
  .withRequestContext("com.notesyncdemo.client.MyRequestFactory$RegistrationInfoRequest")
  .build());
withOperation(new OperationKey("IlFUxNu7$p0I3azHvD1WCs1XxtI="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/notesyncdemo/shared/TaskProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/notesyncdemo/server/Task;)V")
  .withMethodName("deleteTask")
  .withRequestContext("com.notesyncdemo.shared.NoteSyncDemoRequest")
  .build());
withOperation(new OperationKey("mQg_H0H62itsDWpJgXbEm9Jq_2k="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("queryTasks")
  .withRequestContext("com.notesyncdemo.shared.NoteSyncDemoRequest")
  .build());
withOperation(new OperationKey("7wyz6bk3HVvdglaetJTOPcknlds="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Lcom/notesyncdemo/server/Task;")
  .withMethodName("createTask")
  .withRequestContext("com.notesyncdemo.shared.NoteSyncDemoRequest")
  .build());
withOperation(new OperationKey("2Kd7uXbvhdBGCxoX$wctj8mfRYg="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/Long;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/Long;)Lcom/notesyncdemo/server/Task;")
  .withMethodName("readTask")
  .withRequestContext("com.notesyncdemo.shared.NoteSyncDemoRequest")
  .build());
withOperation(new OperationKey("MaLVcuq7jBst_fhHq_OTenSpbOo="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/notesyncdemo/shared/TaskProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/notesyncdemo/server/Task;)Lcom/notesyncdemo/server/Task;")
  .withMethodName("updateTask")
  .withRequestContext("com.notesyncdemo.shared.NoteSyncDemoRequest")
  .build());
withRawTypeToken("8KVVbwaaAtl6KgQNlOTsLCp9TIU=", "com.google.web.bindery.requestfactory.shared.ValueProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withRawTypeToken("95u8AzxM9pCnIX9I4tEPYn0GQUw=", "com.notesyncdemo.shared.MessageProxy");
withRawTypeToken("1zx51br3C8eqLmk$tQ2NPjWcNEM=", "com.notesyncdemo.shared.RegistrationInfoProxy");
withRawTypeToken("Mgw7CEWCFFKHQFXDn0dehHu2csU=", "com.notesyncdemo.shared.TaskProxy");
withClientToDomainMappings("com.notesyncdemo.server.Message", Arrays.asList("com.notesyncdemo.shared.MessageProxy"));
withClientToDomainMappings("com.notesyncdemo.server.RegistrationInfo", Arrays.asList("com.notesyncdemo.shared.RegistrationInfoProxy"));
withClientToDomainMappings("com.notesyncdemo.server.Task", Arrays.asList("com.notesyncdemo.shared.TaskProxy"));
}}
