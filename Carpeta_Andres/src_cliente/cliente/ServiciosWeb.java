
package cliente;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ServiciosWeb", targetNamespace = "http://servidor/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServiciosWeb {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "guardar", targetNamespace = "http://servidor/", className = "cliente.Guardar")
    @ResponseWrapper(localName = "guardarResponse", targetNamespace = "http://servidor/", className = "cliente.GuardarResponse")
    public boolean guardar(
        @WebParam(name = "arg0", targetNamespace = "")
        JugadorTO arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns cliente.JugadorTO
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "consultar", targetNamespace = "http://servidor/", className = "cliente.Consultar")
    @ResponseWrapper(localName = "consultarResponse", targetNamespace = "http://servidor/", className = "cliente.ConsultarResponse")
    public JugadorTO consultar(
        @WebParam(name = "arg0", targetNamespace = "")
        JugadorBean arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns cliente.JugadorTO
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "consultarPerfil", targetNamespace = "http://servidor/", className = "cliente.ConsultarPerfil")
    @ResponseWrapper(localName = "consultarPerfilResponse", targetNamespace = "http://servidor/", className = "cliente.ConsultarPerfilResponse")
    public JugadorTO consultarPerfil(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "desconectar", targetNamespace = "http://servidor/", className = "cliente.Desconectar")
    @ResponseWrapper(localName = "desconectarResponse", targetNamespace = "http://servidor/", className = "cliente.DesconectarResponse")
    public void desconectar(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<cliente.CartasDisponiblesTO>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "filtrarCartas", targetNamespace = "http://servidor/", className = "cliente.FiltrarCartas")
    @ResponseWrapper(localName = "filtrarCartasResponse", targetNamespace = "http://servidor/", className = "cliente.FiltrarCartasResponse")
    public List<CartasDisponiblesTO> filtrarCartas(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "comercioJugadores", targetNamespace = "http://servidor/", className = "cliente.ComercioJugadores")
    @ResponseWrapper(localName = "comercioJugadoresResponse", targetNamespace = "http://servidor/", className = "cliente.ComercioJugadoresResponse")
    public String comercioJugadores(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3);

    /**
     * 
     * @param arg5
     * @param arg4
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @param arg6
     * @param arg7
     * @param arg8
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "consultarCartasSeleccionadas", targetNamespace = "http://servidor/", className = "cliente.ConsultarCartasSeleccionadas")
    @ResponseWrapper(localName = "consultarCartasSeleccionadasResponse", targetNamespace = "http://servidor/", className = "cliente.ConsultarCartasSeleccionadasResponse")
    public boolean consultarCartasSeleccionadas(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        int arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        int arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        int arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        int arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        int arg8);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "comercioComputadora", targetNamespace = "http://servidor/", className = "cliente.ComercioComputadora")
    @ResponseWrapper(localName = "comercioComputadoraResponse", targetNamespace = "http://servidor/", className = "cliente.ComercioComputadoraResponse")
    public String comercioComputadora(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @return
     *     returns java.util.List<cliente.CartasDisponiblesTO>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retornarCartas", targetNamespace = "http://servidor/", className = "cliente.RetornarCartas")
    @ResponseWrapper(localName = "retornarCartasResponse", targetNamespace = "http://servidor/", className = "cliente.RetornarCartasResponse")
    public List<CartasDisponiblesTO> retornarCartas();

    /**
     * 
     * @param arg0
     * @return
     *     returns cliente.CartaTO
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retornaCarta", targetNamespace = "http://servidor/", className = "cliente.RetornaCarta")
    @ResponseWrapper(localName = "retornaCartaResponse", targetNamespace = "http://servidor/", className = "cliente.RetornaCartaResponse")
    public CartaTO retornaCarta(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "mandarSolicitudTrueque", targetNamespace = "http://servidor/", className = "cliente.MandarSolicitudTrueque")
    @ResponseWrapper(localName = "mandarSolicitudTruequeResponse", targetNamespace = "http://servidor/", className = "cliente.MandarSolicitudTruequeResponse")
    public String mandarSolicitudTrueque(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<cliente.CartasDisponiblesTO>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ofertarCartas", targetNamespace = "http://servidor/", className = "cliente.OfertarCartas")
    @ResponseWrapper(localName = "ofertarCartasResponse", targetNamespace = "http://servidor/", className = "cliente.OfertarCartasResponse")
    public List<CartasDisponiblesTO> ofertarCartas(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<cliente.CartasDisponiblesTO>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retornarCartasOfertadas", targetNamespace = "http://servidor/", className = "cliente.RetornarCartasOfertadas")
    @ResponseWrapper(localName = "retornarCartasOfertadasResponse", targetNamespace = "http://servidor/", className = "cliente.RetornarCartasOfertadasResponse")
    public List<CartasDisponiblesTO> retornarCartasOfertadas(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retirarCartaOfertada", targetNamespace = "http://servidor/", className = "cliente.RetirarCartaOfertada")
    @ResponseWrapper(localName = "retirarCartaOfertadaResponse", targetNamespace = "http://servidor/", className = "cliente.RetirarCartaOfertadaResponse")
    public boolean retirarCartaOfertada(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<cliente.CartasDisponiblesTO>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retornarCartasOfertadasSistemas", targetNamespace = "http://servidor/", className = "cliente.RetornarCartasOfertadasSistemas")
    @ResponseWrapper(localName = "retornarCartasOfertadasSistemasResponse", targetNamespace = "http://servidor/", className = "cliente.RetornarCartasOfertadasSistemasResponse")
    public List<CartasDisponiblesTO> retornarCartasOfertadasSistemas(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<cliente.MensajeTrueque>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retornarMensajes", targetNamespace = "http://servidor/", className = "cliente.RetornarMensajes")
    @ResponseWrapper(localName = "retornarMensajesResponse", targetNamespace = "http://servidor/", className = "cliente.RetornarMensajesResponse")
    public List<MensajeTrueque> retornarMensajes(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "eliminarRespuesta", targetNamespace = "http://servidor/", className = "cliente.EliminarRespuesta")
    @ResponseWrapper(localName = "eliminarRespuestaResponse", targetNamespace = "http://servidor/", className = "cliente.EliminarRespuestaResponse")
    public boolean eliminarRespuesta(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "eliminarMensajeConfirmacion", targetNamespace = "http://servidor/", className = "cliente.EliminarMensajeConfirmacion")
    @ResponseWrapper(localName = "eliminarMensajeConfirmacionResponse", targetNamespace = "http://servidor/", className = "cliente.EliminarMensajeConfirmacionResponse")
    public boolean eliminarMensajeConfirmacion(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @return
     *     returns java.util.List<byte[]>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "publicidad", targetNamespace = "http://servidor/", className = "cliente.Publicidad")
    @ResponseWrapper(localName = "publicidadResponse", targetNamespace = "http://servidor/", className = "cliente.PublicidadResponse")
    public List<byte[]> publicidad();

}